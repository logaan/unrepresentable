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

(defn title [name byline]
  [:div {:class "slide"}
   [:h1 name]
   [:h2 {:class "byline"} byline]])

(defn image [filename]
  [:div {:class "slide"}
   [:img {:height "90%" :src (str "/images/" filename)}]])

(defn code [language filename & fades]
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
   (title
    '("Pancakes with" [:br] "a side of nonsense")
    "Logan Campbell")

   (image "01-diner.jpg")

   (image "02-pancakes.jpg")

   (image "03-drip-coffee.jpg")

   (code "typescript" "01-pancakes.ts"
               [1]
               (range 3 6)
               (range 3 11)
               [12 13]
               [])

   (code "typescript" "02-orders.ts"
               (range 1 6)
               (range 7 11)
               [])

   (image "04-espresso-machine.jpg")

   (code "typescript" "03-espresso.ts"
               [5 6])

   (code "typescript" "04-cappuchino-no-milk.ts"
               [])

   (code "typescript" "05-make-coffee-constructor.ts"
         []
         [5 6])

   (image "05-make-coffee-constructor.png")

   (code "typescript" "06-more-granular-types.ts"
         []
         [6])

   (code "ruby" "07-ruby-new-drink.rb"
         [])

   (code "ruby" "08-class-cappuchino.rb"
         [])

   (code "ruby" "09-irb-unknown-keyword-milk.rb"
         [])

   ))

(def path
  "/Users/logaan/code/typescript/unrepresentable/scratch/src/index.html" )

(defn main []
  (spit path slides))

(main)
