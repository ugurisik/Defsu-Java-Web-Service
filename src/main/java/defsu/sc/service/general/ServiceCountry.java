package defsu.sc.service.general;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import defsu.sc.core.AuthCore;
import defsu.sc.core.MenuCore;
import defsu.sc.core.ObjectCore;
import defsu.sc.core.SuResponse;
import defsu.sc.forms.general.FormCountry;
import defsu.sc.records.general.Country;
import defsu.sc.utils.RequestSplitter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@Tag(name = "Ülke İşlemleri", description = "Get, List, Create, Update, Delete")
public class ServiceCountry {

    @PostMapping("/list")
    public ResponseEntity<SuResponse> list(@RequestBody String jsonBody) {
        SuResponse response = new SuResponse();
        RequestSplitter.ListParams listParams = RequestSplitter.splitRequest(jsonBody);
        ObjectCore.ListResult result = ObjectCore.list(new Country(), listParams.getSearchParam(), listParams.getSortParam(), listParams.getPage(), listParams.getPageSize());



        System.out.println("search: "+listParams.getSearchParam()+ " sort: "+listParams.getSortParam() + " page: "+listParams.getPage() + " pageSize: "+listParams.getPageSize());
        response.setData(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/form")
    public ResponseEntity<SuResponse> form() {
        SuResponse response = new SuResponse();
        response.setData(new FormCountry().build());
        return ResponseEntity.ok(response);
    }




}
