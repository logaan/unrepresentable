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

(defn title [name byline]
  [:div {:class "slide"}
   [:h1 name]
   [:h2 {:class "byline"} byline]])

(defn image [filename]
  [:div {:class "slide"}
   [:img {:height "90%" :src (str "images/" filename)}]])

(defn code [language filename fades]
  (for [lines fades]
    (let [lines (set lines)
          path  (.getPath (io/resource (str "code/" filename)))
          code  (slurp path)]
      [:div {:class "slide"}
       (if (empty? lines)

         [:pre
          [:code {:class (str "language-" language)}
           code]]

         (for [[n line] (map list (range) (split code #"\n"))]
           [:pre
            [:code {:class (str "language-" language " "
                                (if (lines (inc n)) "show" "fade"))}
             line "\n"]]))])))

(defn typescript [file fades]
  (code "typescript" (str file ".ts") fades))

(defn ruby [file fades]
  (code "ruby" (str file ".rb") fades))

(def slides
  (boilerplate
   (title
    '("Pancakes with" [:br] "a side of nonsense")
    "Logan Campbell")

   (image "01-diner.jpg")

   (image "02-pancakes.jpg")

   (image "03-drip-coffee.jpg")

   (typescript "01-pancakes"
               [[1]
                (range 3 6)
                (range 3 11)
                [12 13]
                []])

   (typescript "02-orders"
               [(range 1 6)
                (range 7 11)
                []])

   (image "04-espresso-machine.jpg")

   (typescript "03-espresso"
               [[5 6]])

   (typescript "04-cappuchino-no-milk"
               [[]])

   (typescript "05-make-coffee-constructor"
               [[]
                [5 6]])

   (image "05-make-coffee-constructor.png")

   (typescript "06-more-granular-types"
               [[]
                [6]])

   (ruby "07-ruby-new-drink"
         [[]])

   (ruby "08-class-cappuchino"
         [[]])

   (ruby "09-irb-unknown-keyword-milk"
         [[]])

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
               [[]])))

(def path
  (.getPath (io/resource "index.html")))

(defn main []
  (spit path slides))

(main)
