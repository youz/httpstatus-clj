;;; -*- coding: utf8n -*-
(ns noirtest.views.welcome
  (:require [noirtest.views.common :as common])
;            [noir.content.pages :as pages])
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

(comment
  (defpage "/welcome" []
	   (common/layout
	    [:p "Welcome to noirtest"]))
  )

(defpage "/*" []
	 (common/layout
	  [:p "jissai hayai"]))
