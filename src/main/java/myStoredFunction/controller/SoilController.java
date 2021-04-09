package myStoredFunction.controller;

import lombok.extern.slf4j.Slf4j;
import myStoredFunction.dto.SoilDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class SoilController implements ConnectInterface {

    @GetMapping("/")
    public String SoilList(Model model) throws IOException {
        List<SoilDto> soilDtoList = new ArrayList<>(ConnectInterface.myConnection());
        model.addAttribute("soilDtoList", soilDtoList);
        return "index";
    }

}
