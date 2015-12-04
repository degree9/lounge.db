(ns lounge.db
  (:require [castra.core :refer [defrpc ex *session*]]
            [monger.core :as mg]
            [monger.collection :as mc]
            [clj-uuid :as uuid]))

(def dbconn (let [uri (or (System/genenv "LOUNGE_DB") "mongodb://lounge_db:27017")
                  ]
              (mg/connect-via-uri uri)))

(defn find-map [coll doc]
  (mc/find-one-as-map (:db dbconn) coll doc))

(defn find-maps
  ([coll]
   (mc/find-maps (:db dbconn) coll))
  ([coll doc]
   (mc/find-maps (:db dbconn) coll doc)))

(defn insert [coll doc]
  (mc/insert-and-return (:db dbconn) coll doc))
