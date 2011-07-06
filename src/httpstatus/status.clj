(ns httpstatus.status
  (:require [noir.response :as resp]
            [noir.content.css :as ncc])
  (use noir.core
       cssgen
       hiccup.core
       hiccup.page-helpers))

(defpartial layout [& content]
  (html5
   [:head
    [:title "httpstatus.clj"]
    (include-css "/css/reset.css")
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
     ]]
   [:body
    [:div#wrapper
     content]]))

(def location "http://quiet-fog-825.herokuapp.com/")

(def statuses
     {
      200 "OK"
      201 "Created"
      202 "Accepted"
      203 "Non-Authoritative Information"
      204 {:desc "No Content"}
      205 {:desc "Reset Content"}
      206 "Partial Content"
      300 "Multiple Choices"
      301 {:headers {"Location" location}
           :body "Moved Permanently"}
      302 {:headers {"Location" location}
           :body "Found"}
      303 {:headers {"Location" location}
           :body "See Other"}
      304 {:desc "Not Modified"}
      305 {:headers {"Location" location}
           :body "Use Proxy"}
      306 "Unused"
      307 {:headers {"Location" location}
           :body "Temporary Redirect"}
      400 "Bad Request"
      401 {:headers {"Www-Authenticate" "Basic realm=\"Fake Realm\""}
           :body "Unauthorized"}
      402 "Payment Required"
      403 "Forbidden"
      404 "Not Found"
      405 "Method Not Allowed"
      406 "Not Acceptable"
      407 {:headers {"Proxy-Authenticate" "Basic realm=\"Fake Realm\""}
           :body "Proxy Authentication Required"}
      408 "Request Timeout"
      409 "Conflict410 Gone"
      411 "Length Required"
      412 "Precondition Required"
      413 "Request Entity Too Large"
      414 "Request-URI Too Long"
      415 "Unsupported Media Type"
      416 "Requested Range Not Satisfiable"
      417 "Expectation Failed"
      418 "I'm a Teapot"
      500 "Internal Server Error"
      501 "Not Implemented"
      502 "Bad Gateway"
      503 "Service Unavailable"
      504 "Gateway Timeout"
      505 "HTTP Version Not Supported"
      })

(defn make-res [code]
  (let [res (statuses code :unknown)]
    (cond (= res :unknown) (make-res 400)
          (string? res) {:status code :body res}
          (map? res)
          (let [head (res :headers)
                body (res :body)]
            (conj {:status code :body body}
                  (if head {:headers head}))))))

(defn site-index []
  {
   :headers {"Content-Type" "text/html; charset=UTF-8"}
   :body (layout
          [:h1 "HTTP Statuses Test"]
          [:ul
           (map (fn [code]
                  (let [v (statuses code)
                        desc (if (string? v) v (v :desc (v :body)))]
                    [:li (link-to (str "/" code) (str code " " desc))]))
                (sort (keys statuses)))
           ]
	  [:br]
	  [:span "powered by " (link-to "http://www.webnoir.org/" "Noir")])
   })

(defpage "/" []
  (site-index))

(defpage "/:code" {:keys [code]}
  (if (every? #(<= 48 (int %) 57) code)
    (make-res (read-string code))
    (make-res 400)))
