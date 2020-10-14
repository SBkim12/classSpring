package poly.util;

public class EncryptTest{
	public static void main(String[] args)throws Exception{
		System.out.println("----------------------------");
		System.out.println("�빐�떆 �븫�샇�솕 �븣怨좊━利�");
		//�븫�샇�솕 臾몄옄�뿴
		String str = "�븫�샇�솕�븷 臾몄옄�뿴";
		
		//蹂듯샇�솕媛� 遺덇��뒫�븳 �빐�떆�븫�샇�솕 �븣怨좊━利� �떎�뻾
		String hashEnc = EncryptUtil.encHashSHA256(str);
		
		//�빐�떆 �븫�샇�솕 �븣怨좊━利� 寃곌낵 異쒕젰
		System.out.println("hashEnc : "+ hashEnc);
		
		System.out.println("----------------------------");
		
		System.out.println("AES128-CBC ");
		
		//AES128-CBC �븫�샇�솕�븣怨좊━利� �떎�뻾
		String enc = EncryptUtil.encAES128CBC(str);
		
		//AES128-CBC �븫�샇�솕�븣怨좊━利� 寃곌낵 異쒕젰
		System.out.println("enc : "+ enc);
		
		//AES128-CBC 蹂듯샇�솕 �븣怨좊━利� �떎�뻾
		String dec = EncryptUtil.decAES128CBC(enc);
		
		//AES128-CBC 蹂듯샇�솕 �븣怨좊━利� 寃곌낵 異쒕젰
		System.out.println("dec : "+ dec);
		
		System.out.println("---------------------------");
	}
}