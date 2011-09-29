(ns clojure-meetup.http-server)
(use 'lamina.core 'aleph.http)

(defn hello-world [channel request]
  (enqueue channel
    {:status 200
     :headers {"content-type" "text/html"}
     :body "Hello World!"}))

(defn -main []
  (start-http-server hello-world {:port 8080}))

