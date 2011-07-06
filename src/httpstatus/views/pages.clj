(ns httpstatus.views.pages
  (require [noir.content.css :as ncc]
           [httpstatus.views.common :as common]
           [httpstatus.models.status :as status])
  (use noir.core
       cssgen
       hiccup.core
       hiccup.page-helpers))


(defn site-index []
  {
   :headers {"Content-Type" "text/html; charset=UTF-8"}
   :body (common/layout
          [:h1 "HTTP Statuses Test"]
          [:ul
           (map (fn [code]
                  (let [v (status/statuses code)
                        desc (if (string? v) v (v :desc (v :body)))]
                    [:li (link-to (str "/" code) (str code " " desc))]))
                (sort (keys status/statuses)))
           ]
          [:br]
          [:p (link-to "https://github.com/youz/httpstatus-clj" "sources")]
          [:p "powered by " (link-to "http://www.webnoir.org/" "Noir")])
   })

(defpage "/" []
  (site-index))

(defpage "/:code" {:keys [code]}
  (if (every? #(<= 48 (int %) 57) code)
    (status/make-res (read-string code))
    (status/make-res 400)))
