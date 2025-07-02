package ch09.p460_p462;

class CheckBox {
    OnSelectListener listener;

    void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    void select() {
        listener.onSelect();
    }

    static interface OnSelectListener {
        void onSelect();
    }
}

public class Exam3{
    public static void main(String[] args) {
        CheckBox checkBox = new CheckBox();
        checkBox.setOnSelectListener(
            new CheckBox.OnSelectListener() {
                @Override
                public void onSelect() {
                    System.out.println("배경을 변경합니다.");
                }
            }
        );
        checkBox.select();
    }
}
