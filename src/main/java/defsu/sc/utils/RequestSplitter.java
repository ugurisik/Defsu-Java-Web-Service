package defsu.sc.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestSplitter {
    static Logger logger = LoggerFactory.getLogger(RequestSplitter.class);

    public static ListParams splitRequest(String jsonString){
        ListParams listParams = new ListParams();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);

            if (rootNode.has("page")) {
                listParams.page = rootNode.get("page").asInt();
            }
            if (rootNode.has("pageSize")) {
                listParams.pageSize = rootNode.get("pageSize").asInt();
            }
            if (rootNode.has("sortParam")) {
                listParams.sortParam = rootNode.get("sortParam").asText();
                if(listParams.sortParam.isEmpty()){
                    listParams.sortParam = null;
                }
            }
            if (rootNode.has("searchParam")) {
                listParams.searchParam = rootNode.get("searchParam").asText();
                if(listParams.searchParam.isEmpty()){
                    listParams.searchParam = null;
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return listParams;
    }



    public static class ListParams{
        private int page;
        private int pageSize;
        private String sortParam;
        private String searchParam;

        public int getPage() {
            return page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public String getSortParam() {
            return sortParam;
        }

        public String getSearchParam() {
            return searchParam;
        }
    }
}
