package com.example.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    AddressRepository addressRepository;

    @RequestMapping("/")
    public String listAddresses(Model model){
        model.addAttribute("addresses", addressRepository.findAll());
        return "list";
    }

    @RequestMapping("/modify")
    public String listAddressesForUpdate(Model model){
        model.addAttribute("addresses", addressRepository.findAll());
        return "modify";
    }

    @RequestMapping("/delete")
    public String listAddressesForDelete(Model model){
        model.addAttribute("addresses", addressRepository.findAll());
        return "delete";
    }

    @RequestMapping("/search")
    public String getSearchString(){
        return "search";
    }

    @PostMapping("/searchlist")
    public String searchRepository(@RequestParam String searchStr, Model model){

            List<Address> list = addressRepository.findAddressesByEmailContainsOrNameContainsOrPhoneContains(searchStr,
                    searchStr, searchStr);
            model.addAttribute("list", list);
        return "searchedlist";
    }


    @GetMapping("/add")
    public String addressForm(Model model){
        model.addAttribute("address", new Address());
        return "addressform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Address address, BindingResult result){
        if(result.hasErrors()){
            return "addressform";
        }

        addressRepository.save(address);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("address", addressRepository.findOne(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("address", addressRepository.findOne(id));
        return "addressform";
    }
    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id, Model model){
        addressRepository.delete(id);
        return "redirect:/";
    }
}
