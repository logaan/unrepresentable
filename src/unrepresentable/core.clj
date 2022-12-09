(ns unrepresentable.core
  (:require [hiccup.core :refer [html]]
            [clojure.string :refer [split]]))

(defn boilerplate [& slides]
  (html
   [:html
    [:head
     [:link {:rel "stylesheet" :href "/css/hybrid.min.css"}]
     [:link {:rel "stylesheet" :href "/css/slideshow.css"}]]
    [:body
     slides
     [:script {:src "/js/highlight.min.js"}]
     [:script "hljs.highlightAll();"]
     [:script {:src "/js/slideshow.js"}]]]))

(defn title-slide [name byline]
  [:div {:class "slide"}
   [:h1 name]
   [:h2 {:class "byline"} byline]])

(defn image-slide [filename]
  [:div {:class "slide"}
   [:img {:height "90%" :src (str "/images/" filename)}]])

(defn code-slide [language filename & fades]
  (for [lines fades]
    (let [lines (set lines)
          path (str "/Users/logaan/code/clojure/unrepresentable/src/unrepresentable/"
                    filename)
          code (slurp path)]
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

(def slides
  (boilerplate
   (title-slide
    '("Pancakes with" [:br] "a side of nonsense")
    "Logan Campbell")

   (image-slide "01-diner.jpg")

   (image-slide "02-pancakes.jpg")

   (image-slide "03-drip-coffee.jpg")

   (code-slide "typescript" "pancakes.ts"
               [1]
               (range 3 6)
               (range 3 11)
               [12 13]
               [])))

(def path
  "/Users/logaan/code/typescript/unrepresentable/scratch/src/index.html" )

(defn main []
  (spit path slides))

(main)
