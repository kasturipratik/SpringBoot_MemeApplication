package com.example.week5;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    MemeRepository memeRepository;

    @RequestMapping("/")
    public String listMemes(Model model){
        model.addAttribute("memes", memeRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String newActor(Model model){
        model.addAttribute("meme", new Meme());
        return "form";
    }

    @PostMapping("/process")
    public String processActor( @ModelAttribute("meme") @Valid  Meme memes,BindingResult result,
                               @RequestParam("file")MultipartFile file){

        if(result.hasErrors()){
            return "form";
        }

        if(file.isEmpty()){
            return "redirect:/add";
        }
        try{

            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));

            memes.setImage(uploadResult.get("url").toString());
            // return the today's  month, day,and year
            LocalDate todayDate = LocalDate.now();
            memes.setTodayDate(todayDate);

            //set the description to 20 length
            memes.setShortDesc(memes.lengthOfString(memes.getDescription()));

           memeRepository.save(memes);

        }catch(IOException e){
            e.printStackTrace();
            return "redirect:/add";
        }
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showMeme(@PathVariable("id") long id, Model model){
        model.addAttribute("memes",memeRepository.findById(id).get());
        return "detail";
    }

    @RequestMapping("/update/{id}")
    public String updateMeme(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("meme", memeRepository.findById(id));
        return "form";
    }

    @RequestMapping("/delete/{id}")
    public String delMeme(@PathVariable("id") long id){
        memeRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/like/{id}")
    public String likeButton(@PathVariable("id") long id, Meme meme){
        meme= memeRepository.findById(id).get();
        meme.setLikeCount(meme.getLikeCount()+1);
        memeRepository.save(meme);
        return "redirect:/";
    }


}
