(ns unrepresentable.core
  (:require [hiccup.core :refer [html]]
            [clojure.string :refer [split]]
            [clojure.java.io :as io]))

(defn boilerplate [& slides]
  (html
   [:html
    [:head
     [:link {:rel "stylesheet" :href "css/hybrid.min.css"}]
     [:link {:rel "stylesheet" :href "css/slideshow.css"}]]
    [:body
     slides
     [:script {:src "js/highlight.min.js"}]
     [:script "hljs.highlightAll();"]
     [:script {:src "js/slideshow.js"}]]]))

(defn slide [& content]
  [:div {:class "slide"}
   content])

(defn title
  ([name]
   (title name nil))
  ([name byline]
   (slide
    [:h1 name]
    (if byline [:h2 {:class "byline"} byline]))))

(defn image [filename]
  (slide
   [:img {:height "90%" :src (str "images/" filename)}]))


(defn code-block [lines language code]
  (if (empty? lines)

    [:pre
     [:code {:class (str "language-" language)}
      code]]

    (for [[n line] (map list (range) (split code #"\n"))]
      [:pre
       [:code {:class (str "language-" language " "
                           (if (lines (inc n)) "show" "fade"))}
        line "\n"]])))

(defn code-slides [language filename fades title]
  (for [lines fades]
    (let [lines (set lines)
          path  (.getPath (io/resource (str "code/" filename)))
          code  (slurp path)]
      (slide
       (if title [:h1 title])
       (code-block lines language code)))))

(defn typescript
  ([file fades title]
   (code-slides "typescript" (str file ".ts") fades title))
  ([file fades]
   (typescript file fades nil)))

(defn ruby
  ([file fades title]
   (code-slides "ruby" (str file ".rb") fades title))
  ([file fades]
   (ruby file fades nil)))

(def slides
  (boilerplate
   (title
    '("Pancakes with" [:br] "a side of nonsense")
    "Logan Campbell")

   ;; Set the scene

   (image "01-diner.jpg")

   (image "02-pancakes.jpg")

   (image "03-drip-coffee.jpg")

   ;; Counting types

   (typescript "01-pancakes"
               [[1]
                (range 3 6)
                (range 7 11)
                ;; Ask the audience how many types of food we have.
                ;; Then how many types of drink.
                ;; And finally how many types of drink
                []])

   (image "06-tea-batch-brew-options.png")

   ;; Espresso complication

   (image "04-espresso-machine.jpg")

   ; We've added two more beverages now how many types of drink do we have?
   (typescript "03-espresso"
               [[5 6]])

   (image "07-espresso-options.png")

   ;; Nonsense

   (typescript "04-cappuchino-no-milk"
               [[]
                [4 5]])

   (image "08-espresso-options-invalid.png")

   (title "16 Options" "12.5% Nonsense")

   ;; Nonsense breads complexity

   (typescript "05-make-coffee-constructor"
               [[]
                [5]])

   (image "05-make-coffee-constructor.png")

   ;; This all applies outside of types languages

   (ruby "07-ruby-new-drink"
         [[]])

   (ruby "17-make-coffee-constructor"
         [[]])

   ;; What's the alternative? Remove the nonsense.

   (typescript "06-more-granular-types"
               [[]
                [6]])

   (image "09-nonsense-removed.png")

   (title "14 Options" "No nonsense")

   (ruby "17-make-coffee-constructor"
         [[]])

   (ruby "08-class-cappuchino"
         [[]])

   ;; Real examples

   (image "10-blurry-intersection.jpg")

   (typescript "11-user-role"
               [(range 1 5)
                (range 6 10)]
               "Vague types")

   (image "11-twin-ducks.jpg")

   (typescript "13.0-valid-no-delete"
               [(range 1 4)
                (range 5 8)]
               "Related fields")

   (typescript "13.1-valid-soft-delete"
               [(range 5 8)]
               "Related fields")

   (typescript "13.2-invalid-soft-delete"
               [(range 5 8)]
               "Related fields")

   (typescript "13.3-change-to-better"
               [(range 5 10)]
               "Related fields")

   (typescript "14.0-better-no-delete"
               [(range 7 9)]
               "Related fields")

   (typescript "14.1-better-soft-delete"
               [(range 7 10)]
               "Related fields")

   (typescript "14.2-better-invalid-delete"
               [(range 7 10)]
               "Related fields")

   (image "12-car-crash.jpg")

   (typescript "15-parcel"
               [[]
                [2]
                ;; If lost, better be shipped
                [3 4]
                ;; Same for delivery
                [3 5]
                ;; And if it was delivered it shouldn't be lost
                [4 5]
                ;; If it was returned it must have been shipped
                [3 6]
                ;; And not been lost
                [4 6]
                ;; And have been delivered
                [5 6]
                ;; And you had better have a reason for return
                [6 7 8]
                ;; But definately only one reason
                [7 8]
                (range 2 9)
                ]
               "State machine car crash")

   (typescript "16-delivery-status"
               [[]])

   ;; TODO: new slides
   ;; "In fact, if we ignore dates are pratically infinite, our original parcel
   ;; design has 5 possible state values, 2 shipped values, 3 lost during
   ;; delivery values, 2 delivered, 2 returned, 3 dead on arrival return, 3
   ;; change of mind return. If we multiply that out it's 1080 states. And how
   ;; many of them are valid?"
   ;;
   ;; 6. Only 6. That's 99.44% nonsense. Over the life time of a project you're
   ;; practically gaurenteed to mess it up. And if you wanted to write the
   ;; validation logic to ensure that the data is correct? And the unit tests to
   ;; make sure the validation logic is right. It'll be long, complex, no fun to
   ;; work on, and probably wrong.
   ;;
   ;; I hope this example doesn't feel contrived to you. We've got code that
   ;; follows this exact pattern at Zendesk. If you're using a single databse
   ;; table to represent a state machine then you might want to think through
   ;; whether your code could do with a visit to the mechanic.

   (title '("Data types are" [:br] "countable"))

   (title '("Code complexity is" [:br] "measurable"))

   (title '("Simple data means" [:br] "simple code"))

   (title '("Simple data means" [:br] "less bugs"))

   (title '("Simple data means" [:br] "less to think about"))

   (title "Thank you")

   (title "Questions?")

   ))

(def path
  (.getPath (io/resource "index.html")))

(defn main []
  (spit path slides))

(main)
