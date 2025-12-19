import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capacity-rules")
public class TeamCapacityRuleController {

    private final TeamCapacityRuleService service;

    public TeamCapacityRuleController(TeamCapacityRuleService service) {
        this.service = service;
    }

    @PostMapping
    public TeamCapacityConfig create(@RequestBody TeamCapacityConfig rule) {
        return service.createRule(rule);
    }

    @GetMapping("/team/{teamName}")
    public TeamCapacityConfig get(@PathVariable String teamName) {
        return service.getRuleByTeam(teamName);
    }
}
