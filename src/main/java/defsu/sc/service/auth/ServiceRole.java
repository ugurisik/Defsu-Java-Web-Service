package defsu.sc.service.auth;

import defsu.sc.core.AuthCore;
import defsu.sc.core.SuResponse;
import defsu.sc.forms.general.FormCountry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@Tag(name = "Rol İşlemleri", description = "Get, List, Create, Update, Delete")
public class ServiceRole {


    @PostMapping("/component-list")
    @Operation(summary = "Kullanıcı Rolleri", description = "Kullanıcı rolleri için tüm formlara ait kategorize edilmiş bir şekilde component listesi sunar.")
    public ResponseEntity<SuResponse> complist() {
        SuResponse response = new SuResponse();

        AuthCore.addForm(new FormCountry());
        List<AuthCore.FormComponentInfo> components = AuthCore.getAuthorisationComponents();
        response.setData(components);
        return ResponseEntity.ok(response);
    }
}
