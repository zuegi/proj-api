package ch.wesr.projectz.projapi.feature.project.infrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMembersInfo {
    List<String> userIdList;
}
