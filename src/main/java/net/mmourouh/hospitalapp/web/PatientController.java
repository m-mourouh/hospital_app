package net.mmourouh.hospitalapp.web;


import jakarta.validation.Valid;
import net.mmourouh.hospitalapp.entities.Patient;
import net.mmourouh.hospitalapp.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class PatientController {
    PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @RequestMapping("/user/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "4") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword
                           ) {
        Page<Patient> patientsPage = patientRepository.findByNameContains(keyword, PageRequest.of(page, size));

        model.addAttribute("patientsList", patientsPage);
        model.addAttribute("pages", new int[patientsPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/admin/deletePatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePatient(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "page", defaultValue = "0") int p,
                         @RequestParam(name = "keyword", defaultValue = "") String kw
                         ) {
        try {
            patientRepository.deleteById(id);
            return "redirect:/user/index?page=" + p + "&keyword=" + kw ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/user/index";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }

    @RequestMapping("/admin/patient-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String patientForm(Model model){
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }

    @PostMapping(path = "/admin/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult){
        if (bindingResult.hasErrors())  return "patient-form";
        patientRepository.save(patient);
        model.addAttribute("patient", patient);
        return "redirect:/admin/patient-form";
    }

    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(Model model,
                              @RequestParam(name = "id") Long id,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "keyword", defaultValue = "") String keyword
                              ){
        Patient patient = patientRepository.findById(id).orElse(null);
        model.addAttribute("patient", patient);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "edit-patient";
    }

    @PostMapping(path = "/admin/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(Model model,
                       @RequestParam(name = "page", defaultValue = "0") int p,
                       @RequestParam(name = "keyword", defaultValue = "") String kw,
                       @Valid Patient patient,
                       BindingResult bindingResult){
        if (bindingResult.hasErrors())  return "edit-patient";
        patientRepository.save(patient);
        return "redirect:/user/index?page=" + p + "&keyword=" + kw;
    }
}
