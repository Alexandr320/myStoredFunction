package myStoredFunction.controller;

import lombok.extern.slf4j.Slf4j;
import myStoredFunction.entity.Soil;
import myStoredFunction.dao.SoilDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class SoilController {
    @Autowired
    private SoilDao soilDao;

    public SoilController(SoilDao soilDao) {
        this.soilDao = soilDao;
    }

    @GetMapping("/")
    public String SoilList(Model model) {
        List<Soil> soilList = soilDao.findAll();
        model.addAttribute("soilList", soilList);
        return "index";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        model.addAttribute("soil", new Soil());
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Soil soil) {
        soilDao.insert(soil);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("soil", soilDao.findById(id));
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Soil soil) {
        soilDao.update(soil);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        soilDao.delete(id);
        return "redirect:/";
    }

}
