(ns unrepresentable.core
  (:require [hiccup.core :refer [html]]
            [clojure.string :refer [join split]]
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
     [:script {:src "js/common.js"}]
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


(defn code-block [lines language code ndrop]
  (if (empty? lines)

    [:pre
     [:code {:class (str "language-" language)}
      (->> (split code #"\n")
           (drop ndrop)
           (join "\n"))]]

    (for [[n line] (map list (range) (drop ndrop (split code #"\n")))]
      [:pre
       [:code {:class (str "language-" language " "
                           (if (lines (inc n)) "show" "fade"))}
        line "\n"]])))

(defn code-slides
  ([language filename fades title ndrop]
   (for [lines fades]
     (let [lines (set lines)
           path  (.getPath (io/resource (str "code/" filename)))
           code  (slurp path)]
       (slide
        (if title [:h1 title])
        (code-block lines language code ndrop)))))
  ([language filename fades title]
   (code-slides language filename fades title 0)))

(defn kotlin
  ([file fades title drop]
   (code-slides "kotlin" (str file ".kts") fades title drop))
  ([file fades]
   (kotlin file fades nil 0)))

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
    ;; Pre-show

    ;; Over the years I've done a lot of presentations. And they've mostly been
    ;; technical ones..
    '("Pancakes with" [:br] "a side of nonsense")
    "Logan Campbell")

   ;; full of photos and code
   (image "16-sketch-bulk-export.jpg")

   ;; But it's time consuming to do good quality code slides. Getting the syntax
   ;; highliting to work in propper slideshow software
   (image "19-keynote-copy-paste.jpg")

   ;; And then going through and formatting transitions in the code
   (typescript "01-pancakes"
               [(range 3 6)
                ;; So that people know what you're talking about, and don't get
                ;; confused. The worst thing is when you make a change and have
                ;; to go back through everything to update it all. Hopefully not
                ;; introducing any bugs.
                (range 7 11)])

   ;; So I wrote my own slideshow generator. It lets me write all my code
   ;; snippets in actual source files. They could even be unit tested. And in my
   ;; slide definitions
   (image "15-typescript-example.png")

   ;; I can pull in that code, and it will automatically syntax highlight it for
   ;; me. I can also specify which parts of the code should be highlighted.
   (image "14-slide-code.png")

   ;; And it gives me remote control features so I can see how long I've been
   ;; talking for and what the upcoming slide is, kind of like keynote's one
   ;; that you can see here.
   (image "18-keynote-next-slide.jpg")

   ;; I thought you might enjoy seeing behind the curtain a little. But it looks
   ;; like everyone should be here now so we can start for real.

   ;; Today I want to talk with you about how I've been thinking about
   ;; structuring data recently, and about how we can talk about code quality in
   ;; objective ways.

   ;; To do that I've got a little example:
   (title
    '("Pancakes with" [:br] "a side of nonsense")
    "Logan Campbell")

   ;; We're going to model the menu of a diner, think something like Denny's.
   (image "01-diner.jpg")

   ;; They serve some food like pancakes.
   (image "02-pancakes.jpg")

   ;; And a variety of drinks. Tea,
   (image "13-tea.jpg")

   ;; and American style drip coffee.
   (image "03-drip-coffee.jpg")

   ;; So lets code up their menu. We've got a very small selection of food.
   (kotlin "01-pancakes"
           [(range 1 4)

            ;; A couple of drinks
            (range 5 9)

            ;; And when you're ordering your drink you'll be asked whether you
            ;; want it with milk and sugar.
            (range 10 15)

            ;; We've got ourselves a little program here, and we can use these
            ;; data definitions to express a number of different menu items. The
            ;; nice thing about this example is that it's simple enough that we
            ;; can count them.

            ;; Can someone tell me how many types of food we have?
            ;; Good. And we can see that there are two type of beverage.
            ;; But how many possible drinks are there?
            []])

   ;; Thats right. I've laid them out in a table here. We can have either tea,
   ;; or batch brew. And for each of those drinks we can have it with, or
   ;; without sugar (that's the columns in this table), and the rows are with
   ;; or without milk.
   (image "06-tea-batch-brew-options.png")

   ;; But this is a diner in Melbourne. And to compete with the local cafes they
   ;; decide to get an espresso machine.
   (image "04-espresso-machine.jpg")

   ;; So we can update our menu, adding in the new Beverages that they're able
   ;; to prepare.
   ;;
   ;; So now how many drinks are we able to prepare? Before we had 8, now we
   ;; have..
   (kotlin "03-espresso"
               [[7 8]])
   ;; 16. So what was quite a small code change has doubled the possible states
   ;; that our program could be in. If we were writing tests for some functions
   ;; that work with drinks then going from 8 to 16 means we might not be
   ;; writing automated tests for every possible case any more..
   ;;
   ;; Now I want you to think about this very unusual order that we're now able
   ;; to express.
   (image "07-espresso-options.png")

   ;; Nonsense
   (kotlin "04-cappuchino-no-milk"
               [[]
                [5 6]]
               nil 17)
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

   ;; Related fields
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

   ;; State machine car crash
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
   ;; TODO: Focus on each individual state
   (typescript "16-delivery-status"
               [[]])
   (typescript "18-parcel-count"
               [[]])
   (title '("1080 options" [:br] "6 valid states") "99.4% Nonsense")

   ;; TODO: Nulls
   ;; TODO: A thing that changes over time, but maybe you forgot to update the other?
   ;; TODO: Arbitrary setters
   ;; TODO: Departure date and trip length

   ;; Summarise
   (title '("Data types are" [:br] "countable"))
   (title '("Code complexity is" [:br] "measurable"))
   (title '("Simple data means" [:br] "simple code"))
   (title '("Simple data means" [:br] "less bugs"))
   (title '("Simple data means" [:br] "less to think about"))

   (slide)

   ;; TODO: User interfaces
   (image "23-user-interfaces.png")

   ;; TODO: Code club
   (title "#mel-code-club")
   (image "23-toy-robot.png")

   (title "Thank you")
   (title "Questions?")

   ;; Cyclomatic complexity
   (image "24-cyclomatic-complexity.png")

   ;; Algebraic data types
   (image "22-set.png")
   (image "20-polygon-disjoint-union.png")
   (image "21-cartesian-product.png")
   (typescript "19-algebraic-pancakes"
               [(range 1 3)
                (range 4 9)
                (range 10 17)
                []])

   (title "Thank you")

   ))

(def path
  (.getPath (io/resource "index.html")))

(defn main []
  (spit path slides))

(main)
