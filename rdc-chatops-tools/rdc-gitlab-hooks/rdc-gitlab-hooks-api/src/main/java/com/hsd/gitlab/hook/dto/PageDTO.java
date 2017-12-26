/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.hsd.gitlab.hook.dto;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;


/**
 * Description
 *
 * @author uname.chen
 * @date ${DATE}
 */
public class PageDTO extends Pagination {

    private static final long serialVersionUID = 1L;

    /**
     * 查询数据列表
     */
    private List records = Collections.emptyList();

    /**
     * 查询参数
     */
    private Map<String, Object> condition;

    public PageDTO() {
        /* 注意，传入翻页参数 */
    }

    public PageDTO(int current, int size) {
        super(current, size);
    }

    public PageDTO(int current, int size, String orderByField) {
        super(current, size);
        this.setOrderByField(orderByField);
    }

    public List getRecords() {
        return records;
    }

    public PageDTO setRecords(List records) {
        this.records = records;
        return this;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public PageDTO setCondition(Map<String, Object> condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder pg = new StringBuilder();
        pg.append(" Page:{ [").append(super.toString()).append("], ");
        if (records != null) {
            pg.append("records-size:").append(records.size());
        } else {
            pg.append("records is null");
        }
        return pg.append(" }").toString();
    }

}
