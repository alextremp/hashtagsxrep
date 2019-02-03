package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.group.AddUserUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.group.GetMembersRankingUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.group.ListGroupsUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.group.RemoveUserUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.group.response.GetMembersRankingResponse;
import cat.xarxarepublicana.hashtagsxrep.domain.error.EntityNotFoundException;
import cat.xarxarepublicana.hashtagsxrep.domain.group.Group;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GroupController {

    private final ListGroupsUseCase listGroupsUseCase;
    private final GetMembersRankingUseCase getMembersRankingUseCase;
    private final AddUserUseCase addUserUseCase;
    private final RemoveUserUseCase removeUserUseCase;

    @Autowired
    public GroupController(ListGroupsUseCase listGroupsUseCase, GetMembersRankingUseCase getMembersRankingUseCase, AddUserUseCase addUserUseCase, RemoveUserUseCase removeUserUseCase) {
        this.listGroupsUseCase = listGroupsUseCase;
        this.getMembersRankingUseCase = getMembersRankingUseCase;
        this.addUserUseCase = addUserUseCase;
        this.removeUserUseCase = removeUserUseCase;
    }

    @GetMapping("/group")
    @Secured("ROLE_ADMIN")
    public String list(Model model) {
        List<Group> groupList = listGroupsUseCase.listGroups();
        model.addAttribute("groupList", groupList);
        return "group";
    }

    @GetMapping("/group/{groupId}")
    @Secured("ROLE_ADMIN")
    public String detail(
            @PathVariable("groupId") String groupId,
            Model model) {
        GetMembersRankingResponse getMembersRankingResponse = getMembersRankingUseCase.listRankedUsers(groupId);
        model.addAttribute("group", getMembersRankingResponse.getGroup());
        model.addAttribute("ranking", getMembersRankingResponse.getRanking());
        return "group-detail";
    }

    @PostMapping("/group/{groupId}/add")
    @Secured("ROLE_ADMIN")
    public String addUser(
            @PathVariable("groupId") String groupId,
            @RequestParam("nickname") String nickname,
            Model model) {
        try {
            addUserUseCase.addUser(groupId, StringUtils.trim(nickname));
        } catch (EntityNotFoundException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("target", "/group/" + groupId);
            return "form-error";
        }
        return "redirect:/group/" + groupId;
    }

    @PostMapping("/group/{groupId}/remove")
    @Secured("ROLE_ADMIN")
    public String removeUser(
            @PathVariable("groupId") String groupId,
            @RequestParam("nickname") String nickname) {
        removeUserUseCase.remove(groupId, StringUtils.trim(nickname));
        return "redirect:/group/" + groupId;
    }
}
