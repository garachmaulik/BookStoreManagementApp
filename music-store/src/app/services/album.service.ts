import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AlbumModel } from '../album.model';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {
  albumList : AlbumModel[] = [];

  constructor(private router : Router, private http : HttpClient) { }

  addAlbum(album : AlbumModel){
    this.http.post("http://localhost:8080/add", album).subscribe(data => data = album);
  }

  listAlbum(){
    return this.http.get<AlbumModel[]>("http://localhost:8080/get");
  }

  deleteAlbum(index : number) : AlbumModel[] {
    return this.albumList.splice(index, 1);
  }
  update(index : number) {
    this.router.navigate(['edit'], {queryParams: {index: index}});
  }

  sortByTitle() {
    this.albumList.sort((a,b) => a.title > b.title ? 1 : (
      (a.title < b.title ? -1 : 0)
    ));
    return this.albumList;
  }

  sortByPrice() {
    this.albumList.sort((a,b) => a.price > b.price ? 1 : (
      (a.price < b.price ? -1 : 0)
    ));
    return this.albumList;
  }

  searchByArtist(artist : string) {
    return this.albumList.find(x => x.artist == artist);
  }

  searchById(id : number) {
    return this.albumList.find(x => x.id == id);
  }

  getByIndex(index: number) {
    return this.albumList[index];
  }
}
