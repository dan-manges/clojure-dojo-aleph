(ns clojure-meetup.twitter)

(use 'aleph.http 'lamina.core 'aleph.formats)

(defn make-request []
  (sync-http-request
      {:method :get
       :basic-auth ["aleph_example" "_password"]
       :url "http://stream.twitter.com/1/statuses/sample.json"
       :delimiters ["\r"]}))

(defn make-ch [response]
  (map* decode-json (:body response)))

(defn print-tweet [tweet]
;  (clojure.pprint/pprint tweet))
  (printf "@%-20s %s\n" (:screen_name (:user tweet)) (:text tweet)))

(defn -main []
  (let [response (make-request)
        ch (make-ch response)]
    (receive-all ch #(print-tweet %))))

