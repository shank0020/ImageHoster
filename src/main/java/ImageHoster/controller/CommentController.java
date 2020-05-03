package ImageHoster.controller;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class CommentController {

    @RequestMapping(name = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@RequestParam("comment") Comment comment, @PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String title) {
        return "";
    }
}
