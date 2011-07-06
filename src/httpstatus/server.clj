(ns httpstatus.server
  (:require [noir.server :as server
	     httpstatus.status]))

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (printf "mode%s port:%d\n" mode port)
    (server/start port {:mode mode
                        :ns 'httpstatus})))
