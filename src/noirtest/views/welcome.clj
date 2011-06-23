;;; -*- coding: utf8n -*-
(ns noirtest.views.welcome
  (:require [noirtest.views.common :as common])
;            [noir.content.pages :as pages])
  (use noir.core
       hiccup.core
       hiccup.page-helpers))


(defpage "/*" []
  {
   :headers {"Content-Type" "text/html; charset=UTF-8"}
   :body   (common/layout
	    [:p "実際ハヤイ"])
   })
