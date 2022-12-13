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


   (ruby "08-class-cappuchino"
         [[]])

   (ruby "09-irb-unknown-keyword-milk"
         [[]])

   ;; Add a new slide here that shows the problem with allowing milk in the
   ;; order

   (ruby "10-irb-safe-for-lactose-intollerant-customer"
         [(range 1 4)
          (range 5 8)
          []])

   (typescript "11-user-role"
               [(range 1 5)
                (range 6 10)])

   (typescript "12-years-of-experience"
               [(range 1 8)
                (range 9 18)])

   (typescript "13-soft-delete"
               [(range 1 4)
                (range 5 9)
                (range 10 14)
                (range 15 19)])

   (typescript "14-better-soft-delete"
               [(range 1 5)
                (range 6 8)
                (range 9 13)
                (range 14 18)])

   (typescript "15-parcel"
               [[]])

   (typescript "16-delivery-status"
               [[]])

   (title '("Data types are" [:br] "countable"))

   (title '("Code complexity is" [:br] "measurable"))

   (title '("Simple data means" [:br] "simple code"))

   (title '("Simple data means" [:br] "less bugs"))

   ))

(def path
  (.getPath (io/resource "index.html")))

(defn main []
  (spit path slides))

(main)
