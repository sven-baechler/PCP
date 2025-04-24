(ns woche3.uebung.parser
  (:require
   [clojure.core :refer [defn-]]
   [clojure.string :as str]
   [clojure.test :refer [deftest is run-tests]]))

; Expression grammar:

; Expr      → Term ("+" Term | "-" Term) *
; Term      → Factor ("*" Factor | "/" Factor) *
; Factor    → Unary ("^" Factor) ?  
; Unary     → "-" Unary | Base
; Base      → Number
;             | "x"
;             | "(" Expr ")"
;             | Func "(" Expr ")"
;             | Constant
; Func      → "sin" | "cos" | "tan" | "log" | "exp" | "sqrt" 
; Constant  → "e" | "pi"

; these are the functions we support
(def functions #{"sin" "cos" "tan" "log" "exp" "sqrt"})

; we only support one variable
(def var-names #{"x"})

; we support some constants
(def consts #{"e" "pi"})

; AST nodes

; Op: operator, left operand, right operand
(defrecord Op [op left right])

; Var: represents a named variable (x in our case)
(defrecord Var [name])

; Val: represents a value (Number)
(defrecord Val [value])

; Const: represents a named constant (e, pi)
(defrecord Const [name])

; Func: represents a function (sin, cos, etc.)
; func is the function name, arg is the argument to the function (another expression)
(defrecord Func [func arg])

; tokenize the input
(def regexp #"\d+(\.\d+)?|[+-\\*/^()]|[a-z]+|.")

(defn- remove-whitespace [input]
  (str/replace input #"\s+" ""))

(defn- tokenize [input]
  (map first (re-seq regexp (remove-whitespace input))))

; forward declarations for recursive definitions
(declare number expr term factor unary base)

; Expr → Term ("+" Term | "-" Term)*
(defn- expr [tokens]
  (let [[term1 rst1] (term tokens)]
    (loop [acc term1
           [op & rst :as all] rst1]
      (if (#{"+" "-"} op)  ; Check if the operator exists in the map
        (let [[next-term rst-next] (term rst)]
          (recur (Op. op acc next-term) rst-next))
        [acc all]))))

; Term → Factor ("*" Factor | "/" Factor)*
(defn- term [tokens]
  (let [[factor1 rst1] (factor tokens)]
    (loop [acc factor1
           [op & rst :as all] rst1]
      (if (#{"*" "/"} op)  ; Check if the operator is * or /
        (let [[next-factor rst-next] (factor rst)]
          (recur (Op. op acc next-factor) rst-next))
        [acc all]))))

; Factor → Unary ("^" Factor)?
(defn- factor [tokens]
  (let [[base rst] (unary tokens)]
    (if (= "^" (first rst))
      (let [[factor rst2] (factor (rest rst))]
        [(Op. "^" base factor) rst2])
      [base rst])))

; Unary → "-" Unary | Base
(defn- unary [tokens]
  (if (= "-" (first tokens))
    (let [[base rst] (unary (rest tokens))]
      [(Op. "-" (Val. 0) base) rst])
    (base tokens)))

; Base → Number | "x" | "(" Expr ")" | Func "(" Expr ")" | "e" | "pi"
(defn- base [tokens]
  (let [fst (first tokens) rst (rest tokens)]
    (cond
    ; Number
      (re-matches #"\d+(\.\d+)?" fst)
      (number tokens)

    ; "(" Expr ")" 
      (= "(" fst)
      (let [[expr rst] (expr rst)]
        (if (= ")" (first rst))
          [expr (rest rst)]
          (throw (Exception. "missing closing parenthesis"))))

    ; "x"
      (contains? var-names fst)
      [(Var. fst) rst]

    ; "e" | "pi"
      (contains? consts fst)
      [(Const. fst) rst]

    ; Func "(" Expr ")"
      (contains? functions fst)
      (if (= "(" (second tokens))
        (let [[arg rst] (expr (rest rst))]
          (if (= ")" (first rst))
            [(Func. fst arg) (rest rst)]
            (throw (Exception. "missing closing parenthesis"))))
        (throw (Exception. "missing opening parenthesis")))
      :else (throw (Exception. (str "Unknown token " fst))))))

; Number
(defn- number [tokens]
  (try
    (if (.contains (first tokens) ".")
      (let [n (parse-double (first tokens))]  ; als double parsen, wenn "." enthalten
        [(Val. n) (rest tokens)])

      (let [n (parse-long (first tokens))]
        [(Val. n) (rest tokens)]))      ; sonst als long parsen
    (catch Exception _
      (throw (Exception. "Number expected!")))))


; the main parse function
(defn parse [input]
  (let [[result rst] (expr (tokenize input))]
    (if (empty? rst)
      result
      (throw (Exception. (str "Unexpected input " (first rst)))))))

; some tests
(deftest parser-tests
  (is (= (parse "1") (Val. 1)))
  (is (= (parse "1.5") (Val. 1.5)))
  (is (= (parse "x") (Var. "x")))
  (is (= (parse "e") (Const. "e")))
  (is (= (parse "pi") (Const. "pi")))
  (is (= (parse "(1 + 2)") (Op. "+" (Val. 1) (Val. 2))))
  (is (= (parse "(((((((1)) + (((2))))))))") (Op. "+" (Val. 1) (Val. 2))))
  (is (= (parse "(1 + 2) * 3")
         (Op. "*"
              (Op. "+" (Val. 1) (Val. 2))
              (Val. 3))))
  (is (= (parse "(1 + 2) * 3 - 4 / 5")
         (Op. "-"
              (Op. "*"
                   (Op. "+"
                        (Val. 1)
                        (Val. 2))
                   (Val. 3))
              (Op. "/"
                   (Val. 4)
                   (Val. 5)))))
  (is (= (parse "2 ^ 3 ^ 4")
         (Op. "^"
              (Val. 2)
              (Op. "^"
                   (Val. 3)
                   (Val. 4)))))
  (is (= (parse "--5")
         (Op. "-"
              (Val. 0)
              (Op. "-"
                   (Val. 0)
                   (Val. 5)))))
  (is (= (parse "sin(x)")
         (Func. "sin" (Var. "x"))))
  ; cos
  (is (= (parse "cos(x+x)")
         (Func. "cos" (Op. "+" (Var. "x") (Var. "x")))))
  ; tan
  (is (= (parse "tan(x^2)")
         (Func. "tan" (Op. "^" (Var. "x") (Val. 2)))))
  (is (= (parse "log(x^2)")
         (Func. "log" (Op. "^" (Var. "x") (Val. 2)))))
  (is (= (parse "sqrt(2)")
         (Func. "sqrt" (Val. 2))))
  (is (= (parse "1 ^ 2")
         (Op. "^" (Val. 1) (Val. 2))))
  (is (= (parse "exp(1)")
         (Func. "exp" (Val. 1))))
  
  ; check for some failures
  (is (thrown? Exception (parse "")))
  (is (thrown? Exception (parse "1 +")))
  (is (thrown? Exception (parse "1 + 2 &")))
  (is (thrown? Exception (parse "xyz")))
  (is (thrown? Exception (parse "(1 + 2"))))

; run the tests
(run-tests 'SW07.woche3.uebung.parser)


