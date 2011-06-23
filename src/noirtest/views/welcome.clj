(ns noirtest.views.welcome
  (:require [noirtest.views.common :as common]
            [noir.content.pages :as pages])
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to noirtest"]))
