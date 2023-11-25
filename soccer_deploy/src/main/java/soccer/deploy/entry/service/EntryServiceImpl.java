package soccer.deploy.entry.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import soccer.deploy.entry.entity.Entry;
import soccer.deploy.entry.repository.JpaEntryRepository;
import soccer.deploy.match.entity.Match;
import soccer.deploy.user.entity.User;

@Slf4j
@Service

public class EntryServiceImpl implements EntryService {
	@Autowired
	private JpaEntryRepository jpaentryRepository;

	@Override // 최신 경기일정에 해당하는 선수들 가져오기
	public List<Entry> findEntryRecentMatch(Long id) {
		return jpaentryRepository.findAllByMatchId(id);
	}

	@Override
	public List<Entry> SearchRecentEntry(Long id, String name) {
		return jpaentryRepository.findSearchEntry(id, name);
	}

	@Override
	public List<String> findUserAndEntryByMatch(List<Match> match, Long user) {
		List<String> StringList = new ArrayList<String>();
		for (Match match2 : match) {
			List<Long> userId = jpaentryRepository.findAllEntryUserByMatchId(match2.getId());
			if (user.equals(0L)) {
				StringList.add("reg");
			} else if(userId.isEmpty()){
				StringList.add("enroll");
			}else if(userId.contains(user)){
				StringList.add("cancle");
			}else {
				StringList.add("enroll");
			}
		}
		return StringList;
	}

	@Transactional
	@Override
	public void deleteEntry(Long matchId, User user) {
		List<Entry> entrys = jpaentryRepository.findAllByMatchId(matchId);
		for (Entry entry : entrys) {
			if (entry.getUser().equals(user)) {
				jpaentryRepository.delete(entry);
			}
		}
	}

	@Override
	public Boolean indexMatch(Match match, User user) {
		List<Entry> entry = jpaentryRepository.findAllByMatchId(match.getId());
		for (Entry entry2 : entry) {
			if(entry2.getUser().equals(user)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Entry> findEntryUserId(Long userId) {
		return jpaentryRepository.findAllByUserId(userId);
	}

	@Override
	public List<Entry> findEntryMatchId(Long matchId) {
		return jpaentryRepository.findAllByMatchId(matchId);
	}
}
