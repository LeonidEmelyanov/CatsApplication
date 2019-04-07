package ru.demo.catapplication.mvvm.list;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Ккласс для сопоставления класса вью модели и её разметки
 * <br>
 * Типичное использование:
 * <br>
 * <pre>
 * new BaseViewModelsAdapter(new BaseViewTypes(BR.viewModel)
 *         .add(TextViewModel.class, R.layout.text_layout)
 *         .add(ButtonViewModel.class, R.layout.button_layout)
 *         ...);
 * </pre>
 *
 * @author Leonid Emelyanov
 */
public class BaseViewTypes {
    private static final int NO_LAYOUT = -1;
    private static final int NO_VIEW_TYPE = -1;

    /**
     * Идентификатор переменной вью модели в разметке, например BR.viewModel
     */
    private final int mViewModelVariableId;

    /**
     * Хранилище связанных типов вью моделей и их разметки,
     * в дальнейшем используется в адаптере и вью холдере для установки вью модели
     */
    private final List<Pair<Class<?>, Integer>> mStorage = new ArrayList<>();

    /**
     * Конструктор по умолчанию
     *
     * @param viewModelVariableId id переменной вью модели в разметке, например BR.viewModel
     */
    public BaseViewTypes(int viewModelVariableId) {
        mViewModelVariableId = viewModelVariableId;
    }

    /**
     * Добавление нового сопоставление в хранилище
     *
     * @param clazz    класс вью модели
     * @param layoutId id разметки
     * @return текущий экзмепляр класса
     */
    public BaseViewTypes add(@NonNull Class<?> clazz, @LayoutRes int layoutId) {
        mStorage.add(new Pair<Class<?>, Integer>(clazz, layoutId));
        return this;
    }

    /**
     * Получение переменной вью модели в разметке, например BR.viewModel
     */
    public int getViewModelVariableId() {
        return mViewModelVariableId;
    }

    /**
     * Получение id разметки по типу вью
     *
     * @param viewType тип вью
     * @return id разметки, соответсвующее типу вью, либо NO_LAYOUT, если такой тип не найден
     */
    @LayoutRes
    public final int getLayoutId(int viewType) {
        if (viewType >= 0 && viewType < mStorage.size()) {
            return mStorage.get(viewType).second;
        }
        return NO_LAYOUT;
    }

    /**
     * Получение типа вью по типу вью модели, строгое совпадение
     *
     * @param clazz класс вь модели
     * @return тип вью, либо NO_VIEW_TYPE, если такой тип не найден
     */
    public final int getViewType(@NonNull Class<?> clazz) {
        for (int i = 0; i < mStorage.size(); i++) {
            if (mStorage.get(i).first.equals(clazz)) {
                return i;
            }
        }
        return NO_VIEW_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseViewTypes that = (BaseViewTypes) o;
        return mViewModelVariableId == that.mViewModelVariableId &&
                Objects.equals(mStorage, that.mStorage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mViewModelVariableId, mStorage);
    }

    @Override
    public String toString() {
        return "BaseViewTypes{" +
                "mViewModelVariableId=" + mViewModelVariableId +
                ", mStorage=" + mStorage +
                '}';
    }
}
