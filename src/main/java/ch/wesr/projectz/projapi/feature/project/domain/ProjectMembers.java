package ch.wesr.projectz.projapi.feature.project.domain;

import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.shared.exception.BusinessValidationException;
import ch.wesr.projectz.projapi.shared.exception.BusinessValidationMsg;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
// AggregatedRoot
public class ProjectMembers {

    private HashSet<User> memberSet;

    public static ProjectMembers create(List<User> projectMemberList, User projectOwner) {
        ProjectMembers projectMembers = new ProjectMembers();
        projectMembers.memberSet = new HashSet<>();
        if (projectMemberList != null) {
            projectMembers.addAll(projectMemberList);
        }
        projectMembers.add(projectOwner);

        projectMembers.validate();

        return projectMembers;
    }

    public void validate() {
        // nothing to validate until now
    }

    private void add(User projectOwner) {
        this.memberSet.add(projectOwner);
    }

    private void addAll(List<User> projectMemberList) {
        this.memberSet.addAll(new HashSet<>(projectMemberList));
    }
}
