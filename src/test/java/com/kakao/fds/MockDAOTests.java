package com.kakao.fds;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.kakao.fds.dao.IUserLogDAO;
import com.kakao.fds.entity.AccountCreate;
import com.kakao.fds.entity.MoneyTransfer;

/**
 * DAO 레벨 유닛 테스트
 * @author prologue
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MockDAOTests {

	@Mock
	private IUserLogDAO dao;
	long userId = 32445666644334443l;
	
	/**
	 * 사용자 계좌 개설 로그용 메소드 테스트
	 * @throws ParseException
	 */
	@Test
	@SuppressWarnings("deprecation")
	public void searchAccountLogTest() throws ParseException {
		when(dao.getAccountCreateLog(userId)).thenReturn(new AccountCreate(userId, "2018-03-01 01:00:00"));
		
		dao.getAccountCreateLog(userId).getRegdate();
		verify(dao).getAccountCreateLog(userId);
	}	
	
	/**
	 * 송금 로그용 메소드 테스트
	 * @throws ParseException
	 */
	@Test 
	@SuppressWarnings("serial")
	public void searchMoneyTransferLogTest() throws ParseException {
		when(dao.searchMoneyTransferLog(userId)).thenReturn(
			new ArrayList<MoneyTransfer>() {
				{
					this.add(new MoneyTransfer("2018-03-01 01:20:00", userId, 123456789l, "1111111", "2222222", 200_000f, 100_000f));
					this.add(new MoneyTransfer("2018-03-01 01:21:00", userId, 123456789l, "1111111", "2222222", 100_000f, 90_000f));
					this.add(new MoneyTransfer("2018-03-01 01:22:00", userId, 123456789l, "1111111", "2222222", 10_000f, 9_000f));
				}
			}
		);
		dao.searchMoneyTransferLog(userId);
		verify(dao).searchMoneyTransferLog(userId);
	}
}
