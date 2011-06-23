;;; -*- coding: utf8n -*-

(ns noirtest.views.common
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

(defpartial layout [& content]
  (html5
   [:head
    [:title "のわーるテスト"]
    (include-css "/css/reset.css")]
   [:body
    [:div#wrapper
     content]]))
