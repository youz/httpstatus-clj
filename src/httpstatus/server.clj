(ns httpstatus.server
  (:require [noir.server :as server]
	    [httpstatus.status]))

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'httpstatus})))

