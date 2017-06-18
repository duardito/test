package org.com;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;

import java.util.Date;
import java.util.Set;


public class QueryHelper {

    private static BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

    public static BoolQueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public static QueryHelper addFilterDate(String field, Date from, Date to) {
        if (to != null || from != null) {
            addBuilder(field,  from,  to);
        }
        return new QueryHelper();
    }

    public static QueryHelper addFilterString(String field, String desc) {
        if (desc != null) {
            addBuilder(field, desc);
        }
        return new QueryHelper();
    }

    public static QueryHelper addFilterArrayLons(String field, Set<Long> set) {
        if (set != null && !set.isEmpty()) {
            addBuilder(field, set);
        }
        return new QueryHelper();
    }

    private static void addBuilder(String field, String set) {
        QueryBuilder ids = QueryBuilders.termsQuery(field, set);
        queryBuilder.must(ids);
    }

    private static void addBuilder(String field, Set<Long> set) {
        QueryBuilder ids = QueryBuilders.termsQuery(field, set);
        queryBuilder.must(ids);
    }

    private static void addBuilder(String field, Date from, Date to) {
        RangeQueryBuilder query = QueryBuilders.rangeQuery(field).gte(from).lte(to);
        queryBuilder.must(query);
    }
}
