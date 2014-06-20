# cljspark

Clojure wrapper for Spark.

## Installation

```clojure
[cav/cljspark "0.1.0-SNAPSHOT"]
```

## Example

```clojure
(ns sample
  (:require [cav.cljspark :as spark]
            [cav.cljspark.rdd :as rdd]
            [clojure.string :as string]))

(def sc (spark/context :app-name "Sample"
                       :master "local[3]"))

(spark/with-context sc
  (let [file (spark/text-file "hdfs://...")
        word-count (->> file
                        (rdd/mapcat #(string/split % #" "))
                        (rdd/filter (complement string/blank?))
                        (rdd/count))]
    (println "There are " word-count " words.")))
```

## API

You have to browse the source code for now.

## License

(The MIT License)

Copyright (c) 2014 Seth Yuan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
