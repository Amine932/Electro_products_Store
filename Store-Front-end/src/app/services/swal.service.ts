
import Swal from 'sweetalert2';
import {Injectable} from "@angular/core";


@Injectable()
export class SwalService {

  success(text: string) {
    Swal.fire({
      title: "Success",
      text,
      icon: "success"
    });
  }

  warning(text: string) {
    Swal.fire({
      title: "Warning",
      text,
      icon: "warning"
    });
  }

  error(text: string) {
    Swal.fire({
      title: "Oops...",
      text,
      icon: "error"
    });
  }

   confirm(text: string) {
    return Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!"
    });
  }

}
