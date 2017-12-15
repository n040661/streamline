/**
  * Copyright 2017 Hortonworks.
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at

  *   http://www.apache.org/licenses/LICENSE-2.0

  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
 **/
package com.hortonworks.streamline.streams.runtime.storm.bolt.kafka;


import org.apache.storm.kafka.bolt.mapper.TupleToKafkaMapper;
import org.apache.storm.tuple.Tuple;
import com.hortonworks.streamline.streams.StreamlineEvent;
import com.hortonworks.streamline.streams.runtime.storm.StreamlineRuntimeUtil;

public class StreamlineEventToKafkaMapper implements TupleToKafkaMapper {
    private final String keyName;

    public StreamlineEventToKafkaMapper (String keyName) {
        this.keyName = keyName;
    }

    @Override
    public Object getKeyFromTuple (Tuple tuple) {
        if ((keyName == null) || keyName.isEmpty()) {
            return null;
        }
        StreamlineEvent streamlineEvent = (StreamlineEvent) tuple.getValueByField(StreamlineEvent.STREAMLINE_EVENT);
        return StreamlineRuntimeUtil.getFieldValue(streamlineEvent, keyName);
    }

    @Override
    public Object getMessageFromTuple (Tuple tuple) {
        return tuple.getValueByField(StreamlineEvent.STREAMLINE_EVENT);
    }
}
