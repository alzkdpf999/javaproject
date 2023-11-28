package soccer.deploy.match.myService;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.repository.query.Param;

import soccer.deploy.lineUp.entity.LineUp;
import soccer.deploy.match.entity.Match;
import soccer.deploy.match.myDto.MatchDto;
import soccer.deploy.match.myDto.rank;

public interface MatchChoungService {
	public List<rank> rank(String year, String month);
	
	public List<String> year();
	public String month();
	
	public List<MatchDto> matchResult(String year, String month);
	
	public List<Long> findRecentTwoResultMatchId();
	
	public HashMap<String,MatchDto> recentTwoMatchResult(List<Long> id);
	
	public List<Match> findByDate(String year, String month);
	
	public List<Match> findByDateMatch(String year, String month);
	
	
	public HashMap<String,List<rank>> recentTwoMatchPlayer(List<Long> id);
	
	public Match findById(Long id);
	
	public List<LineUp> findLineupResult(Long matchId);
}
