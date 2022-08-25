package m3.furama.util;

import java.util.List;

public class PageHelper<T> {
    public Page<T> listToPage(List<T> list, int total, Pageable pageable){
      return new Page<T>() {
          @Override
          public List<T> getContent() {
              return list;
          }

          @Override
          public int getNumber() {
              return pageable.getPageNum();
          }

          @Override
          public int getPageSize() {
              return pageable.getPageSize();
          }

          @Override
          public int getTotalPages() {
              return  (int) Math.ceil(total / pageable.getPageSize());
          }

          @Override
          public long getTotalElements() {
              return total;
          }

          @Override
          public boolean hasNext() {
              return pageable.getPageNum() < getTotalPages();
          }

          @Override
          public boolean hasPrevious() {
              return pageable.getPageNum() > 1;
          }
      };
    }
}
