import { HeaderDto } from './Header.dto';
import { AuthorDto } from './Author.dto';
import { ReferenceDto } from './Reference.dto';
import { AbstractDto } from './Abstract.dto';

export interface ScientificWorkDto {
   scientificWorkId: string;
   headertDTO : HeaderDto;
   title: string;
   authorsDTO: Array<AuthorDto>;
   abstractDTO: AbstractDto;
   paragraphs : Array<string>;
   referenceDTO : Array<ReferenceDto>;
   comments : Array<String>;

  }

 