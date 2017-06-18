package org.com;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.JestResultHandler;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.DeleteIndex;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.*;

/**
 * Created by edu on 17/06/2017.
 */
public class Juan {


    public JestClient getClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://192.168.99.100:32769")
                .multiThreaded(true)
                .build());
        JestClient client = factory.getObject();
        return client;
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        return cal.getTime();
    }

    private Date especifico(int amount) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, amount);
        return cal.getTime();
    }

    public void delete() throws IOException {
        DeleteIndex indicesExists = new DeleteIndex.Builder("twitter").build();
        getClient().execute(indicesExists);

    }

    public static final String INDEX = "customer-index";
    public static final String MAPPING = "customer-mapping";

    public void index() throws IOException {
        final Pepe source = new Pepe();
        source.setId(123L);
        source.setAlta(especifico(-1));
        source.setApellido("edu3444edrtwere43");
        source.setModificacion(new Date());
        source.setName("mar");

        Index index = new Index.Builder(source).index(INDEX).type(MAPPING).build();

        //getClient().execute(index);


        getClient().executeAsync(index, new JestResultHandler<JestResult>() {
            public void completed(JestResult result) {
                System.out.println(result);
            }

            public void failed(Exception ex) {
                System.out.println("eeeee" + ex.getMessage());
            }
        });

    }

    public void search() throws IOException {

        Pepe pepe = new Pepe();
        pepe.setAlta(especifico(-1));
        pepe.setName("mar");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        //RangeQueryBuilder queryDateAlta = QueryBuilders.rangeQuery("alta").to(pepe.getAlta()).from("2017-06-15");
        //QueryBuilder name = QueryBuilders.termsQuery("name", pepe.getName());

        Set<Long> set = new HashSet<Long>();
        set.add(123L);
        // set.add(456L);
        //QueryBuilder ids = QueryBuilders.termsQuery("id", set);

        // BoolQueryBuilder qb = boolQuery();

        //qb.must(name);
        //qb.must(ids);
        // qb.must(queryDateAlta);

        QueryHelper.addFilterString("name", pepe.getName());
        QueryHelper.addFilterDate("alta", especifico(-3), especifico(-1));
        QueryHelper.addFilterArrayLons("id", set);

        String[] excludes = new String[0];
        String[] includes = new String[2];
        includes[0] = "name";
        includes[1] = "alta";
        searchSourceBuilder.query(QueryHelper.getQueryBuilder()).fetchSource(includes, excludes);

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(INDEX)
                .addType(MAPPING)
                .build();

        SearchResult result = getClient().execute(search);
        List<SearchResult.Hit<Pepe, Void>> hits = result.getHits(Pepe.class);
        List<Pepe> articles = result.getSourceAsObjectList(Pepe.class);
        for (Pepe pepe1 : articles) {
            System.out.println(pepe1);
        }

    }


    public static void main(String[] a) throws IOException {
        Juan j = new Juan();
        j.search();
    }
}
