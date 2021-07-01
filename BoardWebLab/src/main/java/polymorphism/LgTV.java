package polymorphism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
	@Autowired  // 주로 변수 위에 설정하여 해당 타입의 객체를 찾아서 자동으로 할당한다
	//@Qualifier("apple")  // 특정 객체의 이름을 이용하여 의존성 주입할 때 사용한다.
	//@Resource(name = "apple")  // @Autowired와 @Qualifier의 기능을 결합한 어노테이션이다.
	private Speaker speaker;

	public LgTV() {
		System.out.println("===> LgTV 객체 생성됨");
	}

	public void powerOn() {
		System.out.println("LgTV---전원 켠다.");
	}

	public void powerOff() {
		System.out.println("LgTV---전원 끈다.");
	}

	public void volumeUp() {
		//speaker.volumeUp();
		System.out.println("LgTV---소리 올린다.");
	}

	public void volumeDown() {
		//speaker.volumeDown();
		System.out.println("LgTV---소리 내린다.");
	}
}
