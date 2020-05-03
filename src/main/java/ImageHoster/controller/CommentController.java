package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @Autowired
    private ImageService imageService;

    //This controller method is called when the request pattern is of type '/image/{imageId}/{imageTitle}/comments'
    //This method fetch the details such as imageId, imageTitle, loggedUser, commentText and set the Comment class and Model type object
    //This method redirect to images/{imageId}/{imageTitle}
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@RequestParam("comment") String commentText, @PathVariable("imageId") Integer imageId,
                             @PathVariable("imageTitle") String imageTitle, Model model, HttpSession session){

        //Blank comment will redirect to the same page /image/{imageId}/{imageTitle}
        //Only valid comments are allowed to be submitted
        if(commentText.trim().equals("")) {
            return "redirect:/images/" + imageId + "/" + imageTitle;
        }
        else {
            Image image = imageService.getImage(imageId);
            User loggedInuser = (User) session.getAttribute("loggeduser");
            Comment comment = new Comment();
            comment.setImage(image);
            comment.setUser(loggedInuser);
            comment.setText(commentText);
            comment.setCreatedDate(new Date());
            commentService.uploadComment(comment);
            return "redirect:/images/" + image.getId() + "/" + image.getTitle();
        }
    }
}
