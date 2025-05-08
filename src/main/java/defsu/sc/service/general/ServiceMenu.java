package defsu.sc.service.general;

import defsu.sc.core.AuthCore;
import defsu.sc.core.MenuCore;
import defsu.sc.core.SuResponse;
import defsu.sc.forms.general.FormCountry;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@Tag(name = "Menü İşlemleri", description = "Get, List, Create, Update, Delete")
public class ServiceMenu {

    @PostMapping("/list")
    public ResponseEntity<SuResponse> list() {
        SuResponse response = new SuResponse();

        AuthCore.addForm(new FormCountry());

        response.setData(MenuCore.getMenuStructure());
        return ResponseEntity.ok(response);
    }
}
