package com.example.java.controller;

import com.example.java.dto.ClubDto;
import com.example.java.models.Club;
import com.example.java.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }
    @GetMapping("/clubs")
    public String listclubs(Model model){
        List<ClubDto> clubs=clubService.findAllClub();
        model.addAttribute("clubs",clubs);
        return "club-list";
    }
    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") Long clubId,Model model){
        ClubDto clubDto=clubService.findByClubId(clubId);
        model.addAttribute("club",clubDto);
        return "club-detail";

    }
    @GetMapping("/club/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId")Long clubId){
        clubService.deleteClub(clubId);
        return "/redirect:/clubs";
    }
    @GetMapping("/clubs/new")
    public String creatFromClub(Model model){
        Club club=new Club();
        model.addAttribute("club",club);
        return "club-create";
    }
    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club")ClubDto clubDto,BindingResult result,Model model){
        if (result.hasErrors()){
            model.addAttribute("club",clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }
    @GetMapping("/clubs/{clubId}")
    public String editClubForm(@PathVariable("clubId") Long clubId,Model model){
        ClubDto club=clubService.findByClubId(clubId);
        model.addAttribute("club",club);
        return "clubs-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updeteClub(@PathVariable("clubId") Long clubId, @Valid  @ModelAttribute("clubDto") ClubDto club,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "clubs-edit";
        }
        club.setId(clubId);
        clubService.updeteClub(club);
        return "redirect:/clubs";
    }
    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query")String query,Model model){
        List<ClubDto> clubs=clubService.searchClubs(query);
        model.addAttribute("clubs",clubs);
        return "clubs-list";

    }
}
