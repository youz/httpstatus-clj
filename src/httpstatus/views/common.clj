(ns httpstatus.views.common
  (require [noir.content.css :as ncc])
  (use noir.core
       cssgen
       hiccup.core
       hiccup.page-helpers))

(def style
  [:style {:type "text/css"}
   (css
    (rule "body"
          ncc/dark-background
          :padding [:60px :80px]
          :font-family "serif")
    (rule "h1"
          ncc/emphasis
          :font-size :20px
          :margin-bottom :20px)
    (rule "a:link, a:visited"
          :text-decoration :none
          :color :#aae))
   ])

(defpartial layout [& content]
  {
   :headers {"Content-Type" "text/html; charset=UTF-8"}
   :body (html5
          [:head
           [:title "httpstatus.clj"]
           (include-css "/css/reset.css")
           style
           ]
          [:body
           [:div#wrapper
            content]])
   })
