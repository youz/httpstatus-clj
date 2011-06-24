;;; -*- coding: utf8n -*-

(ns noirtest.views.common
  (use noir.core
       cssgen
       noir.content.css
       hiccup.core
       hiccup.page-helpers))

(defpartial layout [& content]
  (html5
   [:head
    [:title "のわーるテスト"]
    (include-css "/css/reset.css")
    [:style {:type "text/css"}
     (css
      (rule "body"
	    dark-background
	    light-text
	    :padding [:60px :80px]
	    :font-family "serif")
      (rule "h1"
	    emphasis
	    :font-size :20px
	    :margin-bottom :20px)
      (rule "a:link, a:visited"
	    :text-decoration :none
	    :color :#aae))
     ]]
   [:body
    [:div#wrapper
     content]]))
