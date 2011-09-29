(ns clojure-meetup.twitter
  (require [aleph.http :as http]
           [lamina.core :as lamina]
           [aleph.formats :as formats]))

(defn make-request []
  (http/sync-http-request
      {:method :get
       :basic-auth ["aleph_example" "_password"]
       :url "http://stream.twitter.com/1/statuses/sample.json"
       :delimiters ["\r"]}))

(defn make-channel [response]
  (lamina/map* formats/decode-json (:body response)))

(defn print-tweet [tweet]
;  (clojure.pprint/pprint tweet))
  (printf "@%-20s %s\n" (:screen_name (:user tweet)) (:text tweet)))

(defn -main []
  (let [response (make-request)
        channel (make-channel response)]
    (lamina/receive-all channel print-tweet)))

