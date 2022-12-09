(ns unrepresentable.core
  (:require [hiccup.core :refer [html]]))

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

(def slides
  (boilerplate
   (title-slide
    '("Pancakes with" [:br] "a side of nonsense")
    "Logan Campbell")

   (image-slide "01-diner.jpg")

   (image-slide "02-pancakes.jpg")

   (image-slide "03-drip-coffee.jpg")

   ))

(def path
  "/Users/logaan/code/typescript/unrepresentable/scratch/src/index.html" )

(defn main []
  (spit path slides))

(main)
