
package local;

public class MenuDeleted extends AbstractEvent {

    private Long id;
    private String menuNm;
    private Long pCnt;
    private String chkDate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuNm() {
        return menuNm;
    }
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public Long getPCnt() {
        return pCnt;
    }
    public void setPCnt(Long pCnt) {
        this.pCnt = pCnt;
    }

    public String getChkDate() {
        return chkDate;
    }
    public void setChkDate(String chkDate) {
        this.chkDate = chkDate;
    }
    
}
